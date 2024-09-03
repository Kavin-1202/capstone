// store.js
import { configureStore } from '@reduxjs/toolkit';
import trainingReducer from './trainingSlice';

const store = configureStore({
  reducer: {
    training: trainingReducer,
  },
});

export default store;
