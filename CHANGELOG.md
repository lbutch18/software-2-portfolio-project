# Changelog

All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.1.0/),
and this project adheres to [Calendar Versioning](https://calver.org/) of
the following form: YYYY.0M.0D.

## 2026.04.24

### Added

- Designed test suite for GolfTracker component
- Designed two different use cases for GolfTracker component

## 2026.04.14

### Added

- Designed kernel implementation for GolfTracker component (GolfTrackerOnSequence)
- Implemented Round nested class and Course nested record

### Updated

- Updated implementation of equals() for GolfTracker to reflect proper practice introduced in class

## 2026.03.31

### Added

- Implemented enhanced methods for GolfTracker component

### Updated

- Updated Javadoc description of replaceRound to reflect actual behavior (adds a copy of newRound, not the actual passed newRound)

## 2026.03.09

### Added

- Designed kernel and enhanced interfaces for GolfTracker component

### Updated

- Changed design to include kernel and extended interfaces
- Updated implementation of calculateHandicap to use other methods instead of roundEntries (designed to be an enhanced method)
- Added methods not currently implemented to the enhanced interface: bestRound, averageDiff, averageScore, roundsAtCourse, and replaceRound

## 2026.02.26

### Added

- Designed a proof of concept for GolfTracker component (I need to come up with a better name)

### Updated

Changed design to include:

- adding and deletion of rounds
- handicap calculation
- main function to demonstrate proof of concept

## 2026.02.05

### Added

- Designed a JobSearchTracker component
- Designed a ResumeControl component
- Designed a GolfTracker component
