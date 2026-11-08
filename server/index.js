import express from 'express';

import { Router } from 'express';

const PORT = 4002;
const app = express();
const router = Router();

router.get('/status', (req, res) => {
    res.json({ message: "Running" });
});

app.use(router);

app.listen(PORT, () => {
    console.log(`Server is running on http://localhost:${PORT}`);
})