import io.vertx.core.Vertx;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.core.json.JsonObject;
public class ThreadInfoApi {

    public static void main(String[] args) {
        Vertx vertx = Vertx.vertx();

        // Router chính
        Router mainRouter = Router.router(vertx);

        // Router phụ: dùng riêng cho /api/*
        Router apiRouter = Router.router(vertx);

        // Định nghĩa route /api/thread trong apiRouter
        apiRouter.get("/thread").handler(ThreadInfoApi::handleThreadInfo);

        // Gắn apiRouter vào mainRouter
        mainRouter.mountSubRouter("/api", apiRouter);

        // Route gốc để test
        mainRouter.get("/").handler(ctx -> ctx.response().end("Main router active"));

        // Khởi chạy server
        vertx.createHttpServer()
                .requestHandler(mainRouter)
                .listen(8888, res -> {
                    if (res.succeeded()) {
                        System.out.println("Server started on port 8888");
                    } else {
                        System.err.println("Failed: " + res.cause());
                    }
                });
    }

    // Handler trả về tên thread hiện tại
    private static void handleThreadInfo(RoutingContext ctx) {
        String threadName = Thread.currentThread().getName();

        JsonObject json = new JsonObject()
                .put("thread", threadName);

        ctx.response()
                .putHeader("Content-Type", "application/json")
                .end(json.encodePrettily());
    }
}
