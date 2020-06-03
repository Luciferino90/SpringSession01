package it.usuratonkachi.springsession.kafkademo.mapper;

import org.mapstruct.MapperConfig;
import org.mapstruct.ReportingPolicy;

@MapperConfig(
        unmappedTargetPolicy = ReportingPolicy.WARN
)
public interface MapperSharedConfig {
}
