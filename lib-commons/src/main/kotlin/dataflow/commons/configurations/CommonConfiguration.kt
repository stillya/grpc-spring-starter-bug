package dataflow.commons.configurations

import com.dataflow.commons.AuthorizationServiceGrpc.AuthorizationServiceBlockingStub
import net.devh.boot.grpc.client.inject.GrpcClient
import net.devh.boot.grpc.client.inject.GrpcClientBean
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.util.*

@GrpcClientBean(
    beanName = "authStub",
    clazz = AuthorizationServiceBlockingStub::class,
    client = GrpcClient("AuthClient")
)
@Configuration
class CommonConfiguration {

    @Bean
    fun decoder(): Base64.Decoder = Base64.getDecoder()
}