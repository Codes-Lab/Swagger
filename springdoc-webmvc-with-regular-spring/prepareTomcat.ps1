$tomcatVersion = "10.1.24"
$tomcatFileName = "apache-tomcat-$tomcatVersion"
$tomcatZip = "$tomcatFileName.zip"
$tomcatUrl = "https://archive.apache.org/dist/tomcat/tomcat-10/v$tomcatVersion/bin/$tomcatZip"
$warFile = "springdoc-webmvc-1.0.war"

# Function to download Tomcat
function Download-Tomcat {
    Write-Host "Downloading Tomcat $tomcatVersion..."
    Invoke-WebRequest -Uri $tomcatUrl -OutFile $tomcatZip
    Write-Host "Download completed."
}

# Check if Tomcat is already downloaded
if (-Not (Test-Path "./$tomcatFileName")) {
    if (-Not (Test-Path "./$tomcatZip")) {
        Download-Tomcat
    }

    # Extract Tomcat
    Write-Host "Extracting Tomcat..."
    Expand-Archive -Path $tomcatZip -DestinationPath .
    Write-Host "Extraction completed."
} else {
    Write-Host "Tomcat $tomcatVersion is already present."
}

# Check if the WAR file exists in the target directory
$warFilePath = Join-Path -Path "target" -ChildPath $warFile
if (-Not (Test-Path $warFilePath)) {
    Write-Host "WAR file not found in the target directory."
    exit
}

# Copy the WAR file to Tomcat's webapps directory
$webappsPath = Join-Path -Path "./$tomcatFileName/webapps" -ChildPath $warFile
Copy-Item -Path $warFilePath -Destination $webappsPath -Force
Write-Host "Copied WAR file to Tomcat's webapps directory."

$startupBat = Join-Path -Path "./$tomcatFileName/bin" -ChildPath "startup.bat"
Write-Host "Tomcat Home: $tomcatHome"
Write-Host "Startup.bat Path: $startupBat"

# Check if the startup.bat file exists
if (Test-Path $startupBat) {
    Write-Host "Starting Tomcat in a new window using startup.bat..."

    # Start the startup.bat script using cmd.exe and open it in a new window
    Start-Process -FilePath "cmd.exe" -ArgumentList "/k", "`"$startupBat`"" -WorkingDirectory (Split-Path $startupBat)
    Write-Host "Tomcat has been started in a new window."
} else {
    Write-Host "Error: startup.bat not found at $startupBat"
}