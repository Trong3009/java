package org.example;

import dagger.Component;
import io.vertx.core.Vertx;

import javax.inject.Singleton;

@Singleton
@Component(modules = VertxModule.class)
public interface AppComponent {
    Vertx getVertx();
}
