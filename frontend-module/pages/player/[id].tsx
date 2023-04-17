import { useRouter } from 'next/router';

const PlayerView = () => {
  const router = useRouter();
  const { id } = router.query;

  return <>{id}</>;
};

export default PlayerView;
