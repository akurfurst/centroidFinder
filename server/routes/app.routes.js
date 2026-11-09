import { Router } from 'express';
import { getDatabase, addToDatabase, resetDatabase } from '../controllers/database_tester.js';

const appRouter = Router();

appRouter.get('/status', (req, res) => {
    res.json({ message: "Running" });
});

appRouter.get('/db', getDatabase)
appRouter.post('/db', addToDatabase)
appRouter.post('/db/reset', resetDatabase)

// appRouter.get("api/videos", getVideos);
// appRouter.post("process/{filename}", processVideo);
// appRouter.get("process/{jobid}/status", getJob);
// appRouter.get("thumbnail/{filename}", getThumbnail);

export default appRouter;