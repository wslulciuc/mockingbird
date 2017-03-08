jvm_app(
  name ='mockingbird',
  basename = 'api',
  deployjar=True,
  binary = ':server'
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
    '3rdparty/jvm:finch-core'
  ]
)
