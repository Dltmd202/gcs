import {createAsyncThunk, createSlice} from "@reduxjs/toolkit";
import userApi from "../api/user";

export const fetchUser = createAsyncThunk('user/fetchUser', async () => {
  const response = await userApi.getUser();
  return response.data.response;
});

export const user = createSlice({
  name: 'user',
  initialState: {loading: false, data: null, error: false},
  reducers: {
    deleteUser: {
      reducer: (state, action) => {
        state.loading = false;
        state.data = action.payload;
        state.error = false;
      },
      prepare: () => ({
        payload: null,
      })
    }
  },
  extraReducers: (builder) => {
    builder.addCase(fetchUser.pending, (state, action) => {
      state.loading = true;
      state.data = null;
      state.error = false;
    })
    builder.addCase(fetchUser.fulfilled, (state, action) => {
      state.loading = false;
      state.data = action.payload;
      state.error = false;
    })
    builder.addCase(fetchUser.rejected, (state, action) => {
      state.loading = false;
      state.data = null;
      state.error = true;
    })
  }
})

export default user;