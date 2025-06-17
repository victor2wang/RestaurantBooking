package com.exercise.application;

import com.exercise.restaurant.controller.BookingController;
import com.exercise.restaurant.controller.UserController;
import com.exercise.restaurant.filter.JwtAuthFilter;
import com.fasterxml.jackson.jakarta.rs.json.JacksonJsonProvider;
import io.muserver.Method;
import io.muserver.MuServer;
import io.muserver.MuServerBuilder;
import io.muserver.rest.CORSConfigBuilder;
import io.muserver.rest.RestHandlerBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static io.muserver.rest.CORSConfigBuilder.corsConfig;


//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class BookingApp {

    static final Logger log= LoggerFactory.getLogger(BookingApp.class);

    public static void main(String[] args) {
        JacksonJsonProvider jacksonJsonProvider = new JacksonJsonProvider();
        //MuServer server = MuServerBuilder.httpServer()
            MuServer server = MuServerBuilder.httpServer().withHttpPort(8080)
                    .addHandler(Method.GET, "/", (request, response, pathParams) -> {
                        response.write("Welcome to the Restaurant Booking System!");
                    })
                    .addHandler(RestHandlerBuilder.restHandler(new BookingController(),new UserController())
                            .addRequestFilter(new JwtAuthFilter())
                            .addCustomReader(jacksonJsonProvider)
                            .addCustomWriter(jacksonJsonProvider)
                            .withOpenApiJsonUrl("/openapi.json")
                            .withCORS(CORSConfigBuilder.corsConfig().withAllowedOrigins("https://petstore.swagger.io")
                                    .withAllowedHeaders("Content-Type"))
                            ).start();
        log.info("Started server at " + server.uri());

    }


}