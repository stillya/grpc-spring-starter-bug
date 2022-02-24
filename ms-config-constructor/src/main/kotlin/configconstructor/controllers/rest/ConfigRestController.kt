package configconstructor.controllers.rest

import dataflow.commons.security.AuthorizationGrpcClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(path = ["/configs"])
class ConfigRestController(val auth: AuthorizationGrpcClient) {

    //
    // Read
    //

    @GetMapping
    fun test(): String {
        val res = auth.getRuleByName("test")
        return res.condition
    }

}