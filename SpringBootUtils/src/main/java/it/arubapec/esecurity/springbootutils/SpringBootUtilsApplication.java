package it.arubapec.esecurity.springbootutils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.core.ResolvableType;
import org.springframework.core.env.Environment;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Component;
import org.springframework.util.*;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.util.WebUtils;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.beans.PropertyDescriptor;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.StreamSupport;

@Slf4j
@SpringBootApplication
@EnableAspectJAutoProxy
public class SpringBootUtilsApplication {

	@Aspect
	@Component
	public static class SimbleAOP {

		@Before("execution(* begin(..))")
		public void before(JoinPoint joinPoint) {
			log.info("-----------");
			log.info("before();");
			log.info(joinPoint.toString());
		}

	}

	@Data
	@AllArgsConstructor
	public static class DemoClass {

		@PostConstruct
		public void begin() {
			log.info("begin();");
		}

		private final List<Map<String, Object>> list = new ArrayList<>();
	}

	@Bean
	DemoClass demoClass(){
		return new DemoClass();
	}

	@Bean CommandLineRunner run(ApplicationContext context, DemoClass demo){
		return args -> {
			Assert.notNull(demo.getList(), "List cannot be null");
			//Assert.notEmpty(demo.getList(), "List cannot be empty");
			Assert.noNullElements(demo.getList(), "Each element of the list must not be null");

			beanUtils(demo);
			classUtils();
			systemPropertiesUtils();
			fileCopyDetails();
			web();
			aop(demo);
			reflection();
			filesystemUtils();
			collectionUtils();
			numberUtils();
			SpringApplication.exit(context, () -> 0);
		};
	}

	private void numberUtils() {
		String number = "10";
		int res = NumberUtils.parseNumber(number, Integer.class);
		log.info(String.format("%s is %d", number, res));
	}

	private void collectionUtils() {
		//CollectionUtils.
	}

	private void filesystemUtils() {
		Path testFolderPath = Paths.get(SystemPropertyUtils.resolvePlaceholders("${user.home}"), "/Desktop/test");

		Path src = testFolderPath.resolve("src");
		Path dest = testFolderPath.resolve("dest");

		Path nestedFolder = src.resolve("nested");

		nestedFolder.toFile().mkdirs();
		String toFile = "ToCopy!";
		try {
			FileCopyUtils.copy(toFile.getBytes(Charset.defaultCharset()), nestedFolder.resolve("test.txt").toFile());
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		try {
			FileSystemUtils.copyRecursively(src, dest);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

		navigateFolder(src.toFile());
		navigateFolder(dest.toFile());

		try {
			FileSystemUtils.deleteRecursively(testFolderPath);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

		navigateFolder(src.toFile());
		navigateFolder(dest.toFile());

	}

	private void navigateFolder(File folder){
		if (folder.isDirectory())
			Arrays.stream(Objects.requireNonNull(folder.listFiles())).forEach(this::navigateFolder);
		else log.info(folder.getPath());
	}

	private void reflection() {
		ReflectionUtils.doWithFields(DemoClass.class, field -> { log.info("Field = " + field.getName()); });
		ReflectionUtils.doWithMethods(DemoClass.class, method -> { log.info("Method = " + method.getName()); });

		Field list = ReflectionUtils.findField(DemoClass.class, "list");
		log.info(list.toString());

		ResolvableType rt = ResolvableType.forField(list);
		log.info(rt.toString());
	}

	private void aop(DemoClass demoClass) {
		Class<?> targetClass = AopUtils.getTargetClass(demoClass);
		log.info("Class<?> is " + targetClass);
		log.info("Is AOP Proxy? " + AopUtils.isAopProxy(demoClass));
		log.info("Is Cjlib Proxy? " + AopUtils.isCglibProxy(demoClass));

	}

	private void web() {
		RestTemplate r = new RestTemplate();
		r.getForEntity("http://localhost:8080/hi", Void.class);
		r.getForEntity("http://localhost:8080/hi?age=18", Void.class);
	}

	private void fileCopyDetails() {
		// Check StreamUtils methods
		String toFile = "Good Work SpringBootFileCopyUtils!";
		File toRead = new File(SystemPropertyUtils.resolvePlaceholders("${user.home}"), "/Desktop/content.txt");
		try {
			FileCopyUtils.copy(toFile.getBytes(Charset.defaultCharset()), toRead);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		try (Reader r = new FileReader(toRead)){
			log.info("Content of file: " + FileCopyUtils.copyToString(r));
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			try {
				Files.deleteIfExists(Paths.get(toRead.getPath()));
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
	}

	private void systemPropertiesUtils() {
		String resolvedText = SystemPropertyUtils.resolvePlaceholders("my home directory is ${user.home}");
		log.info("Resolved text: " + resolvedText);
	}

	private void classUtils() {
		Constructor<DemoClass> demoClassConstructor = ClassUtils.getConstructorIfAvailable(DemoClass.class);
		log.info("demoClassConstructor: " + demoClassConstructor);
		try {
			DemoClass demoClass = demoClassConstructor.newInstance();
			log.info("New instance democlass: " + demoClass);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private void beanUtils(DemoClass demo) {
		for (PropertyDescriptor props : BeanUtils.getPropertyDescriptors(demo.getClass())){
			log.info("pd: " + props.getName());
			if (props.getReadMethod() != null && props.getReadMethod().getName() != null)
				log.info("pd.readMethod" + props.getReadMethod().getName());
		}
	}

	@RestController
	public static class SimpleRestController {
		@GetMapping("/hi")
		void hi(HttpServletRequest request) {
			long age = ServletRequestUtils.getIntParameter(request, "age", -1);
			log.info("Age is: " + age);
			File tempDir = WebUtils.getTempDir(request.getServletContext());
			log.info("Temporary directory: " + tempDir.getPath());
			ApplicationContext applicationContext = WebApplicationContextUtils.getWebApplicationContext(request.getServletContext());
			Environment bean = applicationContext.getBean(Environment.class);
			Environment bean1 = applicationContext.getEnvironment();
			Assert.isTrue(bean == bean1, "Different environment found!");
			log.info("Userhome: " + bean.getProperty("user.home"));

		}
	}


	public static void main(String[] args) {
		SpringApplication.run(SpringBootUtilsApplication.class, args);
	}

}
