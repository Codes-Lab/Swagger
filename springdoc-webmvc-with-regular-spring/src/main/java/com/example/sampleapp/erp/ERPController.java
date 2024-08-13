////////////////////////////////////////////////////////////////////////////////
//
// Created by Abhishek.Mundewal on 8/9/2024.
//
// Copyright (c) 2006 - 2024 FORCAM GmbH. All rights reserved.
////////////////////////////////////////////////////////////////////////////////

package com.example.sampleapp.erp;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 */
@RestController
@RequestMapping("erp")
public class ERPController {

    // ------------------------------------------------------------------------
    // methods
    // ------------------------------------------------------------------------
    @GetMapping("/endpoint")
    public String erpEndpoint() {
        return "ERP Endpoint";
    }
}
