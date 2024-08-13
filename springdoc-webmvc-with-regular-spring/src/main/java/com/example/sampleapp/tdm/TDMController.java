////////////////////////////////////////////////////////////////////////////////
//
// Created by Abhishek.Mundewal on 8/9/2024.
//
// Copyright (c) 2006 - 2024 FORCAM GmbH. All rights reserved.
////////////////////////////////////////////////////////////////////////////////

package com.example.sampleapp.tdm;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 */
@RestController
@RequestMapping("tdm")
public class TDMController {

    // ------------------------------------------------------------------------
    // methods
    // ------------------------------------------------------------------------
    @GetMapping("/endpoint")
    public String tdmEndpoint() {
        return "TDM Endpoint";
    }
}
