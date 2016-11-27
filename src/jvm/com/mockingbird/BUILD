jvm_app(
  name ='mockingbird',
  basename = 'service',
  dependencies = [
    ':server'
  ],
  bundles = [
    bundle(relative_to='config', fileset=globs('config/*'))
  ]
)

jvm_binary(
  name='server',
  main='com.mockingbird.Main',
  dependencies=[
    ':lib'
  ]
)

scala_library(
  name = 'lib',
  sources = ['Main.scala'],
  dependencies = [
    '3rdparty/jvm/com/github/finagle:finch-core'
  ]
)
