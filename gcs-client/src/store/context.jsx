import {createAsyncThunk, createSlice} from "@reduxjs/toolkit";
import contextApi from "../api/context";

export const holdContext = createAsyncThunk(
  `context/holdContext`,
  async (scenarioId) => {
    const response = await contextApi.holdContext(scenarioId);
    return response.data.response;
  }
)

export const fetchContext = createAsyncThunk(
  `context/fetchContext`,
  async () => {
    const response = await contextApi.getRunningContext();
    return response.data.response;
  }
)

export const context = createSlice({
  name: 'context',
  initialState: { loading: true, data: {agents:[]}, error: false },
  reducers: {
    updatePosition: {
      reducer: (state, action) => {
        if(state.loading) return;

        const pos = action.payload.position;
        const key = action.payload.key;
        state.data.agents[key].position = pos;
      }
    },
    update: {
      reducer: (state, action) => {
        if(state.loading) return;

        const {agent, key} = action.payload;
        state.data.agents[key] = agent;
        state.data.agents[key].active = true;

        // state.data.agents[key].ned = agent.ned;
        // state.data.agents[key].battery = agent.battery;
        // state.data.agents[key].angle = agent.angle;
        // state.data.agents[key].velocity = agent.velocity;
      }
    },
    setFocusAgent: {
      reducer: (state, action) => {
        if(state.loading) return;
        state.data.focused = action.payload;
        state.data.isFocused = true;
      }
    },
  },
  extraReducers: (builder) => {
    builder.addCase(holdContext.pending, (state, action) => {
      state.loading = true;
      state.data = {agents:[]};
      state.error = false;
    })
    builder.addCase(holdContext.fulfilled, (state, action) => {
      state.loading = false;
      state.data = action.payload;
      state.error = false;
      state.data.isFocused = false;
      state.data.focused = 0;
    })
    builder.addCase(holdContext.rejected, (state, action) => {
      state.loading = false;
      state.data = {agents:[]};
      state.error = true;
    })
    builder.addCase(fetchContext.pending, (state, action) => {
      state.loading = true;
      state.data = {agents:[]};
      state.error = false;
    })
    builder.addCase(fetchContext.fulfilled, (state, action) => {
      state.loading = false;
      state.data = action.payload;
      state.error = false;
      state.data.isFocused = false;
      state.data.focused = 0;
    })
    builder.addCase(fetchContext.rejected, (state, action) => {
      state.loading = false;
      state.data = {agents:[]};
      state.error = true;
    })
  }
});
export default context;