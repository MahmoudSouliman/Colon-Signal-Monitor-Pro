# Colon-Signal-Monitor-Pro
A real-time biomedical monitoring system for Electrocolonography (EconG) signals, featuring ESP32-based data acquisition and an Android application for live signal processing and visualization in microvolts.

# Electrocolonography (EconG) Real-Time Monitoring System 🩺📊

A specialized biomedical engineering project focused on the acquisition, digital processing, and live visualization of electrical signals from the colon using an **ESP32** and a dedicated **Android application**.

## 🚀 Overview
This system provides a portable and efficient solution for monitoring gastrointestinal electrical activity. It captures raw analog data and applies digital signal processing (DSP) to display calibrated physical values in real-time.

## 🛠️ Hardware Stack
* **Bio-Amplifier:** AD620 Instrumentation Amplifier.
* **Microcontroller:** ESP32 (utilizing high-speed 12-bit ADC).
* **Connectivity:** Bluetooth Classic for wireless data transmission.
* **Electrodes:** Standard medical-grade surface electrodes.

## 📱 Software Features
* **Real-time Data Visualization:** High-performance graphing using the MPAndroidChart library.
* **Digital Signal Processing (DSP):** Implementation of a Low-Pass Filter (LPF) for noise reduction and baseline stability.
* **Physical Calibration:** Real-time conversion of ADC units into **Microvolts (uV)**.
* **Time-Domain Analysis:** X-axis synchronization in **Seconds** for accurate monitoring.

## 📸 Demo
![Project Cover](Media/project_image.png)

## 💻 Technical Parameters
* **Sampling Frequency:** 50 Hz.
* **Filter Coefficient:** 0.15 (Digital Smoothing).
* **Voltage Mapping:** `Voltage(uV) = (Raw_ADC * 3.3 / 4095) * 1000`.

---
---
*Developed by Eng. Mahmoud Souliman - AI/ML Engineer & Medical Equipment Specialist.*
