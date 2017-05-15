// Copyright 2017 Lincoln Clarete
//
// Permission is hereby granted, free of charge, to any person obtaining
// a copy of this software and associated documentation files (the
// "Software"), to deal in the Software without restriction, including
// without limitation the rights to use, copy, modify, merge, publish,
// distribute, sublicense, and/or sell copies of the Software, and to
// permit persons to whom the Software is furnished to do so, subject to
// the following conditions:
//
// The above copyright notice and this permission notice shall be
// included in all copies or substantial portions of the Software.
//
// THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
// EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
// MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
// NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE
// LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION
// OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION
// WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.

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
