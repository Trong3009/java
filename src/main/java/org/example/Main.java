package org.example;
import io.vertx.core.Vertx;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        AppComponent component = DaggerAppComponent.create();
        Vertx vertx = component.getVertx();

        vertx.createHttpServer()
                .requestHandler(req -> req.response().end("Hello "))
                .listen(8888, http -> {
                    if (http.succeeded()) {
                        System.out.println("HTTP server started on port 8888");
                    } else {
                        System.out.println("HTTP server failed: " + http.cause());
                    }
                });
    }
}