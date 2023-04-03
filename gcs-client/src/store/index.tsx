import { combineReducers } from 'redux';
import {persistReducer, persistStore} from 'redux-persist';
import storageSession from 'redux-persist/es/storage/session';
import user from './user';
import context from "./context";
import {configureStore} from "@reduxjs/toolkit";

const persistConfig = {
  key: 'root',
  storage: storageSession,
  whitelist: ['user', 'scenario'],
};

const combineReducer = combineReducers({
  user: user.reducer,
  context: context.reducer
});

const rootReducer = persistReducer(persistConfig, combineReducer);

export const store = configureStore({
  reducer: rootReducer,
  // middleware: [], // add any middleware you want to use here
  // devTools: process.env.NODE_ENV !== 'production',
})

export const persistor = persistStore(store);

export type RootState = ReturnType<typeof store.getState>;