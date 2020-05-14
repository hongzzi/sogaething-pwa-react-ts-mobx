import * as React from 'react';
import styled from 'styled-components';
import Link from 'next/link';

export default () => {
    return(
        <WrapprNav>
            <WrapperNavItem>
                1
            </WrapperNavItem>
            <WrapperNavItem>
                1
            </WrapperNavItem>
            <WrapperNavItem>
                1
            </WrapperNavItem>
            <WrapperNavItem>
                1
            </WrapperNavItem>
            <WrapperNavItem>
                <Link href='/user'>
                    <a>User</a>
                </Link>
            </WrapperNavItem>
        </WrapprNav>
    )
}

const WrapprNav = styled.div`
    position: fixed;
    display: flex;
    justify-content: space-around;
    bottom: 0;
    height: 8%;
    width : 100%;
    background-color: lightblue;
`;

const WrapperNavItem = styled.div`
    background-color: yellow;    
`;

