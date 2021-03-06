/*
 * Copyright 2016 Cisco Systems, Inc.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package com.cisco.oss.foundation.tools.simulator.rest.sub_resources;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.HandlerMapping;

import com.cisco.oss.foundation.tools.simulator.rest.service.SimulatorService;

/**
 * this is the resource of the simulator itself
 * all the REST calls of the simulator will get here
 */
@RestController
@RequestMapping("/**")
@Scope("request")
public class SimulatorResource {

    private static Logger logger = LoggerFactory.getLogger(SimulatorResource.class);
    private SimulatorService simulatorService;

    public SimulatorResource() {
        simulatorService = SimulatorService.getInstance();
    }

    @RequestMapping(method = {RequestMethod.GET})
    public ResponseEntity getResource(HttpServletRequest httpServletRequest,
                                      @RequestHeader HttpHeaders headers,
                                      @RequestParam MultiValueMap<String, String> queryParams,
                                      @RequestBody(required = false) String body) {

        if (body == null) {
            body = "";
        }
        String subResources = (String) httpServletRequest.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE);

        logMethod(RequestMethod.GET.name(), httpServletRequest.getRequestURI(), httpServletRequest.getQueryString(), body);

        ResponseEntity re = simulatorService.retrieveResponse(RequestMethod.GET.name(), httpServletRequest, headers, queryParams, body);

        logResponse(RequestMethod.GET.name(), re);
        return re;

    }

    @RequestMapping(method = {RequestMethod.PUT})
    public ResponseEntity updateResource(HttpServletRequest httpServletRequest,
                                         @RequestHeader HttpHeaders headers,
                                         @RequestParam MultiValueMap<String, String> queryParams,
                                         @RequestBody(required = false) String body) {
        if (body == null) {
            body = "";
        }
        
        String subResources = (String) httpServletRequest.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE);

        logMethod(RequestMethod.PUT.name(), httpServletRequest.getRequestURI(), httpServletRequest.getQueryString(), body);
        ResponseEntity re = simulatorService.retrieveResponse(RequestMethod.PUT.name(), httpServletRequest, headers, queryParams, body);
        logResponse(RequestMethod.PUT.name(), re);
        return re;
    }

    @RequestMapping(method = {RequestMethod.POST})
    public ResponseEntity createResource(HttpServletRequest httpServletRequest,
                                         @RequestHeader HttpHeaders headers,
                                         @RequestParam MultiValueMap<String, String> queryParams,
                                         @RequestBody(required = false) String body) {
        if (body == null) {
            body = "";
        }
        String subResources = (String) httpServletRequest.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE);

        logMethod(RequestMethod.POST.name(), httpServletRequest.getRequestURI(), httpServletRequest.getQueryString(), body);

        ResponseEntity re = simulatorService.retrieveResponse(RequestMethod.POST.name(), httpServletRequest, headers, queryParams, body);

        logResponse(RequestMethod.POST.name(), re);
        return re;
    }

    @RequestMapping(method = {RequestMethod.HEAD})
    public ResponseEntity headResource(HttpServletRequest httpServletRequest,
                                         @RequestHeader HttpHeaders headers,
                                         @RequestParam MultiValueMap<String, String> queryParams,
                                         @RequestBody(required = false) String body) {
        if (body == null) {
            body = "";
        }
        String subResources = (String) httpServletRequest.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE);

        logMethod(RequestMethod.HEAD.name(), httpServletRequest.getRequestURI(), httpServletRequest.getQueryString(), body);

        ResponseEntity re = simulatorService.retrieveResponse(RequestMethod.HEAD.name(), httpServletRequest, headers, queryParams, body);

        logResponse(RequestMethod.HEAD.name(), re);
        return re;
    }
    
    @RequestMapping(method = {RequestMethod.DELETE})
    public ResponseEntity deleteResource(HttpServletRequest httpServletRequest,
                                         @RequestHeader HttpHeaders headers,
                                         @RequestParam MultiValueMap<String, String> queryParams,
                                         @RequestBody(required = false) String body) {
        if (body == null) {
            body = "";
        }
        String subResources = (String) httpServletRequest.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE);

        logMethod(RequestMethod.DELETE.name(), httpServletRequest.getRequestURI(), httpServletRequest.getQueryString(), body);
        ResponseEntity re = simulatorService.retrieveResponse(RequestMethod.DELETE.name(), httpServletRequest, headers, queryParams, body);

        logResponse(RequestMethod.DELETE.name(), re);
        return re;
    }

    private void logMethod(String method, String uriInfo, String queryParams, String body) {
        try {
            logger.debug("Simulator recieved a " + method + " request | "
                    + "path: " + uriInfo + " | "
                    + "queryParams: " + queryParams + "| "
                    + "body: " + body);
        } catch (Exception e) {
            logger.error("failed writing to log");
        }
    }

    private void logResponse(String method, ResponseEntity response) {
        logger.debug("ResponseEntity for " + method + " method: " + response.getStatusCode().value());
    }


}
