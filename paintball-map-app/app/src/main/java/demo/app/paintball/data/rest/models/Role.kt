package demo.app.paintball.data.rest.models

class Role(
    var name: RoleType = RoleType.PLAYER
)

enum class RoleType {
    ADMIN, PLAYER
}