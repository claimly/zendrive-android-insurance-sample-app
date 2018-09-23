## Introduction

This sample app demonstrates integrating the Zendrive SDK and using it to implement ridershare logic with insurance periods.

## Tracking Regulatory Periods

The following insurance periods are tracked by the sample application:

  **Period 1**: The driver or courier is logged into the mobile application, is available for ride requests, but is not yet matched with a passenger (or a good).

  **Period 2**: The driver or courier has accepted a match with a prospective passenger (or good) but that passenger (or good) is not yet physically in the vehicle.

  **Period 3**: The driver or courier has picked up the passenger (or good) and the driver’s vehicle is occupied. This period continues as long as there is a passenger (or good) in the vehicle.