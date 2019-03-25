#!/bin/bash
source /etc/profile

cd $(dirname $0)

exec java -Xmx512M -Xms512M -cp conf:lib/* com.sauzny.metting.MettingApplication