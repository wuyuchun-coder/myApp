#!/usr/bin/env bash
set -euo pipefail

APP_DIR="$(cd "$(dirname "$0")/.." && pwd)"
JAR="$APP_DIR/myApp-bootstrap/target/myApp-bootstrap-1.0.0-SNAPSHOT.jar"
LOG="$APP_DIR/app.log"
PID_FILE="$APP_DIR/app.pid"

export JAVA_HOME="${JAVA_HOME:-/usr/lib/jvm/java-21-openjdk-amd64}"
export MYSQL_PASSWORD="${MYSQL_PASSWORD:-MySQL_root_2026!}"
export PATH="$JAVA_HOME/bin:$PATH"

if [[ ! -f "$JAR" ]]; then
  echo "JAR not found, building..."
  mvn -f "$APP_DIR/pom.xml" -pl myApp-bootstrap -am package -DskipTests -q
fi

if [[ -f "$PID_FILE" ]]; then
  OLD_PID="$(cat "$PID_FILE")"
  if kill -0 "$OLD_PID" 2>/dev/null; then
    echo "Stopping existing process $OLD_PID"
    kill "$OLD_PID"
    sleep 2
  fi
  rm -f "$PID_FILE"
fi

nohup java -jar "$JAR" > "$LOG" 2>&1 &
echo $! > "$PID_FILE"
echo "Started myApp backend (PID $(cat "$PID_FILE")), log: $LOG"
