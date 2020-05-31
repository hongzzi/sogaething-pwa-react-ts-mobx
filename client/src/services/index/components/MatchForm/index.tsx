import * as React from 'react';
import styled from '~/styled';

import CommonBtn from '../CommonBtn';
import CustomIcon from '../CustomIcon';

import ExpandIcon from '../../assets/img/form-expand.png';

export interface IMatchFormProps {
}

export default (props: IMatchFormProps) => {
    return (
        <Wrapper>
            <FormContainer>
                <InputContainer>
                    <CSarea>*</CSarea>
                    <CNarea> 카테고리</CNarea>
                </InputContainer>
                <InputContainer>
                    <CSarea>*</CSarea>
                    <CNarea> 금액</CNarea>
                    <Input type={'number'} />
                    <CICarea>원</CICarea>
                </InputContainer>
                <InputContainer>
                    <CNarea> 거래방법</CNarea>
                </InputContainer>
                <InputContainer>
                    <CNarea> 해시태그</CNarea>
                    <CICarea> <CustomIcon url={ExpandIcon} /> </CICarea>
                </InputContainer>
            </FormContainer>
                <FooterContainer>
                    <CommonBtn type={'disable'} text={'등록하기'} />
                </FooterContainer>
        </Wrapper>
    );
}

const Wrapper = styled.div`
    position: relative;
    width: 100%;
    height: 100%;
    padding: 1.6rem;
    grid-area: CC;
    display: flex;
    flex-direction: column;
    justify-content: space-between;
`

const FormContainer = styled.div`
    width: 100%;
    height: 100%;
`

const InputContainer = styled.div`
    display: grid;
    grid-auto-columns: 1rem 4.8rem auto 3rem;
    grid-template:
    "CS CN CIN CIC";
    align-items: center;
    width: 100%;
    height: 3.5rem;
    border-bottom: solid 1px #ddd;
    margin: 0.5rem 0;
    vertical-align: center;
`

const Input = styled.input`
    grid-area: CIN;
    width: 11rem;
    height: 2rem;
    font-size: 14px;
`

const CSarea = styled.div`
    grid-area: CS;
    color: #ff2d55;
    padding: 3px;
`

const CNarea = styled.div`
    grid-area: CN;
    font-size: 14px;
    display: inline;
    font-weight: bold;
    color: #929292;
`

const CICarea = styled.div`
    grid-area: CIC;
    margin: auto;
`

const ContentsBox = styled.div`
    width: 100%;
    height: auto;
`

const ContentsText = styled.div`
    padding: 0.8rem 0;
    color: #666;
    font-weight: bold;
`

const ContentsArea = styled.textarea`
    width: 100%;
    height: 6rem;
    border: solid 1px #ddd;
    border-radius: 5px;
`

const FooterContainer = styled.div`
    display: flex;
    justify-content: flex-end;
    margin: 1.5rem 0 0 0;
`
