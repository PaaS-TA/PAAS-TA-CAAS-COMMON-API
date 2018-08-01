package org.paasta.caas.common.api.temp;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * The type Temp controller.
 */
@RestController
public class TempController {

    /**
     * Index string.
     *
     * @return the string
     */
    @RequestMapping
    String index() {
        // TODO :: REMOVE
        return "CaaS COMMON API :: TEMPORARY INDEX :: 임시 페이지";
    }
}
