
var histogramSeries = []
histogramSeries.push({name: "R", points: [], color: "red"})
histogramSeries.push({name: "G", points: [], color: "green"})
histogramSeries.push({name: "B", points: [], color: "#4e78c7"})
for (i = 0; i < lists[0].length; i++) {
    histogramSeries[0].points.push({x: i, y: lists[0][i]})
    histogramSeries[1].points.push({x: i, y: lists[1][i]})
    histogramSeries[2].points.push({x: i, y: lists[2][i]})
}

renderChart(histogramSeries);

function renderChart(series) {
	JSC.Chart('chartDiv', {
		title_label_text: 'Histogram of your photo',
		legend: {
		    template: "%icon,%name"
		},
		xAxis_crosshair_enabled: true,
        yAxis: {
            scale: {
                type: 'logarithmic',
                logBase: 5
             },
            defaultTick_label: { visible: false },
        },
		defaultPoint_tooltip: '%seriesName <b>%yValue</b>',
		series: series
	});
}



