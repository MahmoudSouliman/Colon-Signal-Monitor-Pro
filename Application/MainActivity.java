package com.mahmoud.colonsignalmonitor;

import android.os.Bundle;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.ArrayList;

/**
 * Developed by Eng. Mahmoud Souliman
 * Project: Colon Signal Monitor Pro
 * Description: Handles Bluetooth data acquisition and real-time EconG signal visualization.
 */
public class MainActivity extends AppCompatActivity {

    private LineChart mChart;
    private ArrayList<Entry> dataEntries = new ArrayList<>();
    private float timeInSeconds = 0;
    private final float samplingInterval = 0.02f; // 20ms (50Hz)

    // Digital Signal Processing (DSP) Variables
    private float lastFilteredValue = 0;
    private final float alpha = 0.15f; // Smoothing factor for Low-Pass Filter

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize UI Components
        setupChart();
    }

    private void setupChart() {
        mChart = findViewById(R.id.chart1);
        mChart.getDescription().setEnabled(false);
        mChart.getLegend().setEnabled(true);
        // Additional professional chart configurations here...
    }

    /**
     * Processes incoming raw ADC data from ESP32.
     * @param rawData The 12-bit raw value (0-4095) received via Bluetooth.
     */
    private void processIncomingData(String rawData) {
        try {
            int rawValue = Integer.parseInt(rawData.trim());

            // 1. Conversion to Physical Units (Microvolts)
            // Formula: (Raw * ReferenceVoltage / Resolution) * 1000
            float voltageUV = (rawValue * 3.3f / 4095f) * 1000;

            // 2. Applying Digital Low-Pass Filter (Simple Exponential Smoothing)
            float filteredValue = alpha * voltageUV + (1 - alpha) * lastFilteredValue;
            lastFilteredValue = filteredValue;

            // 3. Update the Real-time Graph
            updateChart(timeInSeconds, filteredValue);
            timeInSeconds += samplingInterval;

        } catch (NumberFormatException e) {
            // Handle corrupted data packets
        }
    }

    private void updateChart(float x, float y) {
        dataEntries.add(new Entry(x, y));
        LineDataSet dataSet = new LineDataSet(dataEntries, "Colon Signal (uV)");
        
        // Applying visual styles for a professional look
        dataSet.setMode(LineDataSet.Mode.CUBIC_BEZIER);
        dataSet.setDrawCircles(false);
        dataSet.setLineWidth(2f);

        LineData lineData = new LineData(dataSet);
        mChart.setData(lineData);
        mChart.notifyDataSetChanged();
        mChart.invalidate(); // Refresh the chart
    }
}
