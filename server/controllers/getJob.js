import { findJob } from "../services/db.service.js";

export default function getJob(req, res) {
    const jobId = req.params.jobid;

    try {
        const {status, outputPath} = findJob(jobId);
        if (status === undefined) res.status(404).send({ error: "Error fetching job status" });
        if (status === "processing") res.status(200).send({ status });
        if (status === "done") res.status(200).send({ status, result: outputPath })
        if (status === "error") res.status(200).send({ status, error: "Error processing video: Unexpected ffmpeg error" });
    } catch (e) {
        res.status(500).send({ error: "Error fetching job status" });
    }
    // If the controller reaches this, there's been a serious problem.
    console.error("What did you do.");
}