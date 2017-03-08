jvm_app(
  name='api',
  deployjar=True,
  binary=':server'
)

jvm_binary(
  name='server',
  main='mockingbird.Api',
  dependencies=[
    ':lib'
  ]
)

scala_library(
  name='lib',
  sources=['Api.scala'],
  dependencies=[
    '3rdparty/jvm:finch-core'
  ]
)
