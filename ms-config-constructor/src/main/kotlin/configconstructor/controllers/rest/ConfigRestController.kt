package configconstructor.controllers.rest

import configconstructor.services.facades.ConfigService
import dataflow.commons.security.AuthorizationGrpcClient
import dataflow.libconfigconstructor.dto.ConfigCreateDto
import dataflow.libconfigconstructor.dto.ConfigDto
import dataflow.libconfigconstructor.dto.ConfigUpdateDto
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping(path = ["/configs"])
class ConfigRestController(val configService: ConfigService, val auth: AuthorizationGrpcClient) {

    //
    // Read
    //

    @GetMapping
    fun getConfigsPage(): String {
        val res = auth.getRuleByName("test")
        return res.condition
    }

    //
    // Create
    //


    @PostMapping
    fun createConfig(@RequestBody configCreateDto: ConfigCreateDto): ConfigDto {
        return configService.create(configCreateDto, null)
    }

    //
    // Update
    //

    @PutMapping
    fun updateConfig(@RequestBody configUpdateDto: ConfigUpdateDto): ConfigDto {
        return configService.updateById(configUpdateDto, null)
    }

    //
    // Delete
    //

    @DeleteMapping("/{configId}")
    fun updateConfig(@PathVariable configId: UUID) {
        configService.deleteById(configId)
    }

}