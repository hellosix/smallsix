#!/bin/bash
set encoding=utf-8
CLASSPATH=conf/
for i in lib/*.jar; do
        CLASSPATH=${CLASSPATH}:$i
done
for i in *.jar; do
        CLASSPATH=${CLASSPATH}:$i
done
echo ${CLASSPATH}
java -cp ${CLASSPATH} cn.hellosix.Application >> logs/start.log 2>&1 < /dev/null &
echo $! > pid