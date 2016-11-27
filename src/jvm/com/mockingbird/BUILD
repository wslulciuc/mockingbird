scala_library(
  name = 'mockingbird-lib',
  sources = globs('*.scala'),
  dependencies = [
    '3rdparty/jvm/com/github/finagle:finch-core'
  ]
)

jvm_binary(
  name='mockingbird-server',
  main='com.mockingbird.Main',
  dependencies=[
    ':mockingbird-lib'
  ]
)

