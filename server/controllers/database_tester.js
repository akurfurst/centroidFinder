// This file is designed to test the database.

import { v4 as generateUUID } from 'uuid';
import { addJob, clearJobs, findAllJobs } from "../services/db.service.js";

/**
 * Returns all the jobs in the database. 
 * 
 * 200 - Success
 * 500 - Failure
 */
export function getDatabase(req, res) {
    try {
        const allJobs = findAllJobs();
        res.status(200).json({ count: allJobs.length, jobs: allJobs });
    } catch (e) {
        console.error(e);
        res.status(500).send({ error: "Database error" });
    }
}

/**
 * Adds a generic job to the database.
 * 201 - Success
 * 500 - Failure
 */
export function addToDatabase(req, res) {
    const jobMetadataExample = {
        id: generateUUID(),
        threshold: 1, 
        targetColor: "FFFFFF",
        inputPath: "tmp/path"
    }

    try {
        addJob(jobMetadataExample)
        res.status(201).send();
    } catch (e) {
        console.error(e);
        res.status(500).send({ error: "Database error" });
    }
}

/**
 * Resets the database.
 * 200 - Success
 * 500 - Failure
 */
export function resetDatabase(req, res) {
    try {
        clearJobs()
        res.status(200).send();
    } catch (e) {
        console.error(e);
        res.status(500).send({ error: "Database error" });
    }
}