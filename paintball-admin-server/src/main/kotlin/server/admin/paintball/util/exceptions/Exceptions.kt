package server.admin.paintball.util.exceptions

class EntityNotFoundException(message: String) : RuntimeException(message)

class BadRequestException(message: String) : RuntimeException(message)
