#!/bin/bash

loops=0

last_received=$(cat /proc/net/dev | grep 'enp03s' | awk '{print $2}')
last_transmitted=$(cat /proc/net/dev | grep 'enp03s' | awk '{print $10}')
avg_received=0
avg_transmitted=0

while true; do
	sleep 1
	clear

	#Internet speed connection
	received=$(cat /proc/net/dev | grep 'enp0s3' | awk '{print $2}')
	transmitted=$(cat /proc/net/dev | grep 'enp0s3' | awk '{print $10}')
	dr=$(( received - last_received ))
	dt=$(( transmitted - last_transmitted ))
	avg_received=$(echo "scale=0; (($avg_received * $loops) + $dr) / ($loops + 1)" | bc -l)
	avg_transmitted=$(echo "scale=0; (($avg_transmitted * $loops) + $dt) / ($loops + 1)" | bc -l)
	last_received=$received
	last_transmitted=$transmitted

	if [[ $dr -lt 1024 ]]; then
		echo "RECEIVING SPEED: $dr B/s"
	elif [[ $dr -lt 1048576 ]]; then
		dr=$(echo "scale=2; $dr / 1024" | bc -l)
		echo "RECEIVING SPEED: $dr kB/s"
	else
		dr=$(echo "scale=2; $dr / 1048576" | bc -l)
		echo "RECEIVING SPEED: $dr MB/s"
	fi

	if [[ $dt -lt 1024 ]]; then
		echo "TRANSMITTING SPEED: $dt B/s"
	elif [[ $dt -lt 1048576 ]]; then
		dt=$(echo "scale=2; $dt / 1024" | bc -l)
		echo "TRANSMITTING SPEED: $dt kB/s"
	else
		dt=$(echo "scale=2; $dt / 1048576" | bc -l)
		echo "TRANSMITTING SPEED: $dt MB/s"
	fi

	if [[ $avg_received -lt 1024 ]]; then
		echo "AVERAGE RECEIVING SPEED: $avg_received B/s"
	elif [[ $avg_received -lt 1048576 ]]; then
		avg_received=$(echo "scale=2; $avg_received / 1024" | bc -l)
		echo "AVERAGE RECEIVING SPEED: $avg_received kB/s"
	else
		avg_received=$(echo "scale=2; $avg_received / 1048576" | bc -l)
		echo "AVERAGE RECEIVING SPEED: $avg_received MB/s"
	fi

	if [[ $avg_transmitted -lt 1024 ]]; then
		echo "AVERAGE TRANSMITTING SPEED: $avg_transmitted B/s"
	elif [[ $avg_transmitted -lt 1048576 ]]; then
		avg_transmitted=$(echo "scale=2; $avg_transmitted / 1024" | bc -l)
		echo "AVERAGE TRANSMITTING SPEED: $avg_transmitted kB/s"
	else
		avg_transmitted=$(echo "scale=2; $avg_transmitted / 1048576" | bc -l)
		echo "AVERAGE TRANSMITTING SPEED: $avg_transmitted MB/s"
	fi

	#CPU's usage
	echo "CPU'S USAGE:"
	(echo "Core number|Core usage|Core speed"
	cat /proc/cpuinfo | grep "^processor" | while read cpu_nr
	do
		cpu_nr=$(echo $cpu_nr | awk -F ':' '{print $2}')
		cpu_usage=0
		cpu_speed=$(cat /proc/cpuinfo | grep "MHz" | sed -n $(( cpu_nr + 1 ))p | awk -F ':' '{print $2}')
		echo "$cpu_nr|$cpu_usage%|$cpu_speed MHz"
	done) | column -t -s "|"

	#System working time
	total_time=$(cat /proc/uptime | awk -F '.' '{print $1}')
	days=$(( total_time / 86400 ))
	total_time=$(( total_time - $(( days * 86400 )) ))
	hours=$(( total_time / 3600 ))
	total_time=$(( total_time - $(( hours * 3600 )) ))
	mins=$(( total_time / 60 ))
	total_time=$(( total_time - $(( mins * 60 )) ))
	seconds=$total_time
	echo "SYSTEM WORKING TIME: $days days, $hours hours, $mins minutes, $seconds seconds"

	#Battery percentage
	battery=$(cat /sys/class/power_supply/BAT0/uevent | grep "^POWER_SUPPLY_CAPACITY=" | awk -F '=' '{print $2}')
	echo "BATTERY PERCENTAGE: $battery%"

	#Memory usage
	total_memory=$(cat /proc/meminfo | grep '^MemTotal:' | awk '{print $2, $3}')
	free_memory=$(cat /proc/meminfo | grep '^MemFree:' | awk '{print $2, $3}')
	available_memory=$(cat /proc/meminfo | grep '^MemAvailable:' | awk '{print $2, $3}')
	echo "MEMORY USAGE:"
	echo "Total memory: $total_memory"
	echo "Free memory: $free_memory"
	echo "Available memory: $available_memory"
	total_memory=$(echo $total_memory | awk '{print $1}')
	available_memory=$(echo $available_memory | awk '{print $1}')
	part_of_used_mem=$(echo "($total_memory-$available_memory)/$total_memory*100" | bc -l | awk -F '.' '{print $1}')
	echo "Part of used memory: $part_of_used_mem%"

	loops=$(( loops + 1 ))

done

