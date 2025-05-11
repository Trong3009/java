package org.example;

import dagger.Module;
import dagger.Provides;
import io.vertx.core.Vertx;

import javax.inject.Singleton;

@Module
public class VertxModule {
    @Provides
    @Singleton
    Vertx provideVertx() {
        return Vertx.vertx();
    }
}
