package main

import (
	"fmt"
	"io/ioutil"
	"os"
	"strconv"
	"strings"
)

const AdcFile = "/sys/devices/ocp.3/helper.15/AIN0";

func convert(value []byte) (float64, error) {
	stringValue := strings.TrimSpace(string(value[:]));
	intValue, err := strconv.ParseFloat(stringValue, 0)
	if err != nil {
		return 0.0, err
	}

	millivolts := (intValue / 4096.0) * 1800
	celcius := (millivolts - 500) / 10.0
	return celcius, nil
}

func main() {
	bytes, err := ioutil.ReadFile(AdcFile)
	if err != nil {
		fmt.Printf("Failed reading file %s\n", AdcFile)
		os.Exit(2)	// ENOENT
	}
	converted, err := convert(bytes)
	if err != nil {
		fmt.Printf("Failed convert value `%s' to float\n", bytes)
		os.Exit(-1)
	}
	fmt.Printf("%f\n", converted)
}
