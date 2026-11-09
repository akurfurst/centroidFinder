import { Router } from 'express';
import processVideo from '../controllers/processVideo.js';

const appRouter = Router();

appRouter.get('/status', (req, res) => {
    res.json({ message: "Running" });
});

// appRouter.get("/api/videos", getVideos);
appRouter.post("/process/:filename", processVideo);
// appRouter.get("/process/{jobid}/status", getJob);
// appRouter.get("/thumbnail/{filename}", getThumbnail);

export default appRouter;