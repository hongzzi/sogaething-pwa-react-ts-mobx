import axios, { AxiosResponse } from 'axios';
import { NEXT_APP_REST_ENDPOINT } from '../helpers/config';
import { ENDPOINT, KEYS } from './../constants';

const API_HOST = NEXT_APP_REST_ENDPOINT || ENDPOINT.REST;

export interface IDepositRequestDto {
  apiKey: string;
  bankName: string;
  bankAccountNo: string;
  amount: number;
  message: string;
}

export interface IDepositResponseDto {
  resultType: 'SUCCESS' | 'FAIL';
  success: {
    scheme: string;
    link: string;
  };
}

class BankService {
  API_ENDPOINT = 'https://toss.im/transfer-web/linkgen-api';

  async postDeposit(data: IDepositRequestDto): Promise<AxiosResponse<IDepositResponseDto>> {
    data.apiKey = KEYS.TOSS;
    return axios.post(`${this.API_ENDPOINT}/link`, data);
  }
}

export default BankService;
