import { Router } from 'express';
import { getDatabase, addToDatabase, resetDatabase } from '../controllers/database_tester.js';

const testRouter = Router();

testRouter.get('/db', getDatabase)
testRouter.post('/db', addToDatabase)
testRouter.post('/db/reset', resetDatabase)

export default testRouter;