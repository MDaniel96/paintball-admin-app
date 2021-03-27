package server.admin.paintball

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class PaintballAdminServerApplication

fun main(args: Array<String>) {
	runApplication<PaintballAdminServerApplication>(*args)
}
