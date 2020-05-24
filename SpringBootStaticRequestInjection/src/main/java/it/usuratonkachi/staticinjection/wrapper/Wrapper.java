package it.usuratonkachi.staticinjection.wrapper;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class Wrapper {

    private String requestId;
    private String sessionId;

}
