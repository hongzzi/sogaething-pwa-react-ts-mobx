import { AxiosResponse } from 'axios';

export interface Response<T> {
  data: T,
  msg?: string
}

export type ApiResponse<T> = AxiosResponse<Response<T>>


export interface DataFetchLoading {
  loading: boolean;
}