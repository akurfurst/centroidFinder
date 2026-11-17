import express from 'express';
import appRouter from './routes/app.routes.js';
import testRouter from './routes/test.routes.js';
import cors from 'cors';

const PORT = 4002;
const app = express();

app.use(express.static('public'));

app.use(cors({
    origin: 'http://localhost:5176'
}));
app.use(appRouter);
app.use("/test", testRouter);

app.listen(PORT, () => {
    console.log(`Server is running on http://localhost:${PORT}`);
})