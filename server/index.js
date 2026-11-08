import express from 'express';
import appRouter from './routes/app.routes';

const PORT = 4002;
const app = express();

app.use(appRouter);

app.listen(PORT, () => {
    console.log(`Server is running on http://localhost:${PORT}`);
})