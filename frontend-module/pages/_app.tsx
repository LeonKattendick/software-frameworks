import CustomLayout from '@/components/CustomLayout';
import { ConfigProvider, theme } from 'antd';
import 'antd/dist/reset.css';
import type { AppProps } from 'next/app';
import Head from 'next/head';
import { QueryClient, QueryClientProvider } from 'react-query';
import { ReactQueryDevtools } from 'react-query/devtools';
import '../styles/antd.min.css';
import '../styles/global.css';

const queryClient = new QueryClient({
  defaultOptions: {
    queries: { retry: 1, staleTime: 1000 * 60 * 15 }
  }
});

export default function App({ Component, pageProps }: AppProps) {
  return (
    <>
      <Head>
        <link rel="shortcut icon" href="./favicon.ico" />
      </Head>
      <QueryClientProvider client={queryClient}>
        <ConfigProvider theme={{ algorithm: theme.darkAlgorithm }}>
          <CustomLayout>
            <Component {...pageProps} />
          </CustomLayout>
        </ConfigProvider>
        {process.env.NODE_ENV === 'development' && <ReactQueryDevtools initialIsOpen={false} />}
      </QueryClientProvider>
    </>
  );
}
