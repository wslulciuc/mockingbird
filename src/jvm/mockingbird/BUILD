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
  sources=globs('*.scala'),
  dependencies=[
    '3rdparty/jvm:avro',
    '3rdparty/jvm:cats',
    '3rdparty/jvm:circe-generic',
    '3rdparty/jvm:finagle-commons-stats',
    '3rdparty/jvm:finch-circe',
    '3rdparty/jvm:finch-core',
    '3rdparty/jvm:shapeless',
    '3rdparty/jvm:twitter-server'
  ]
)
