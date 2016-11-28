jvm_app(
  name ='mockingbird',
  basename = 'api',
  dependencies = [
    ':server'
  ],
  bundles = [
    bundle(relative_to='config', fileset=globs('config/*'))
  ]
)

jvm_binary(
  name='server',
  main='com.mockingbird.Api',
  dependencies=[
    ':lib'
  ]
)

scala_library(
  name = 'lib',
  sources = ['Api.scala'],
  dependencies = [
    '3rdparty/jvm/com/github/finagle:finch-core'
  ]
)
