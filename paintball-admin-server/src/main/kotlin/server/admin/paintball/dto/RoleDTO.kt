package server.admin.paintball.dto

import server.admin.paintball.security.authorization.RoleType

class RoleDTO(

    var name: RoleType = RoleType.PLAYER
)
