/*
 * Copyright 2014 Cisco Systems, Inc.
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

package com.cisco.oss.foundation.tools.simulator.rest.resources;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.HttpMethod;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.UriInfo;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.cisco.oss.foundation.tools.simulator.rest.service.SimulatorService;

/**
 * this is the resource of the simulator itself
 * all the REST calls of the simulator will get here
 *
 */
@Component
@Path("/")
@Scope("request")
public class SimulatorResource {

	private static final String subResourcesPath = "{subResources: [:!a-zA-Z0-9~%_/-]+}";
	private SimulatorService simulatorService;

	public SimulatorResource() {
		simulatorService = SimulatorService.getInstance();
	}

	@GET
	@Path(subResourcesPath)
	public Response getResource(@Context HttpServletRequest httpServletRequest, @Context final UriInfo uriInfo, @Context HttpHeaders headers,
			@PathParam("subResources") String subResources, String body) {
		
		ResponseBuilder rb = simulatorService.retrieveResponse(HttpMethod.GET, httpServletRequest, uriInfo, headers, body);

		return rb.build();
	
	}

	@PUT
	@Path(subResourcesPath)
	public Response updateResource(@Context HttpServletRequest httpServletRequest, @Context final UriInfo uriInfo, @Context HttpHeaders headers,
			@PathParam("subResources") String subResources, String body) {
		ResponseBuilder rb = simulatorService.retrieveResponse(HttpMethod.PUT, httpServletRequest, uriInfo, headers, body);

		return rb.build();
	}

	@POST
	@Path(subResourcesPath)
	public Response createResource(@Context HttpServletRequest httpServletRequest, @Context final UriInfo uriInfo, @Context HttpHeaders headers,
			@PathParam("subResources") String subResources, String body) {
		ResponseBuilder rb = simulatorService.retrieveResponse(HttpMethod.POST, httpServletRequest, uriInfo, headers, body);

		return rb.build();
	}

	@DELETE
	@Path(subResourcesPath)
	public Response deleteResource(@Context HttpServletRequest httpServletRequest, @Context final UriInfo uriInfo, @Context HttpHeaders headers,
			@PathParam("subResources") String subResources, String body) {
		ResponseBuilder rb = simulatorService.retrieveResponse(HttpMethod.DELETE, httpServletRequest, uriInfo, headers, body);

		return rb.build();
	}
	
}