# 场景02 双端同时启动脚本
# 用法:在 reedjava/scene 目录下执行  ./scene02-cert-exchange/start-all.ps1
# 说明:分别在两个进程中启动平台方(8082)与资方方(8083),日志输出到 .temp 目录
$ErrorActionPreference = 'Stop'

$sceneRoot = Split-Path -Parent $MyInvocation.MyCommand.Path
$repoRoot = Split-Path -Parent (Split-Path -Parent $sceneRoot)
$logDir = Join-Path $repoRoot '.temp'
if (-not (Test-Path $logDir)) { New-Item -ItemType Directory -Force -Path $logDir | Out-Null }

$platformJar = Join-Path $sceneRoot 'scene02-platform\target\scene02-platform-1.0.0.jar'
$fundJar = Join-Path $sceneRoot 'scene02-fund\target\scene02-fund-1.0.0.jar'

foreach ($jar in @($platformJar, $fundJar)) {
    if (-not (Test-Path $jar)) {
        Write-Error "jar not found: $jar  run 'mvn clean install -DskipTests' in reedjava/scene first"
    }
}

$platform = Start-Process -FilePath 'java' `
    -ArgumentList @('-Dfile.encoding=UTF-8', '-jar', $platformJar) `
    -RedirectStandardOutput (Join-Path $logDir 'scene02-platform.log') `
    -RedirectStandardError (Join-Path $logDir 'scene02-platform.err.log') `
    -PassThru -WindowStyle Hidden

$fund = Start-Process -FilePath 'java' `
    -ArgumentList @('-Dfile.encoding=UTF-8', '-jar', $fundJar) `
    -RedirectStandardOutput (Join-Path $logDir 'scene02-fund.log') `
    -RedirectStandardError (Join-Path $logDir 'scene02-fund.err.log') `
    -PassThru -WindowStyle Hidden

Write-Output "platform(8082) pid=$($platform.Id)   fund(8083) pid=$($fund.Id)"
Write-Output "logs: $logDir"
Write-Output "verify: curl -X POST http://localhost:8082/scene02/api/verify/run -H 'Content-Type: application/json' -d '{}'"
